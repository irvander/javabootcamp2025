package com.example.batch;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.batch.item.xml.StaxEventItemWriter;
import org.springframework.batch.item.xml.builder.StaxEventItemReaderBuilder;
import org.springframework.batch.item.xml.builder.StaxEventItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.sql.init.dependency.DependsOnDatabaseInitialization;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.transaction.PlatformTransactionManager;

import com.example.model.Persona;
import com.example.model.PersonaDTO;
import com.thoughtworks.xstream.security.AnyTypePermission;

@Configuration
public class PersonasBatchConfiguration {
	@Autowired
	JobRepository jobRepository;
	@Autowired
	PlatformTransactionManager transactionManager;

	public FlatFileItemReader<PersonaDTO> personaCSVItemReader(String fname) {
		return new FlatFileItemReaderBuilder<PersonaDTO>().name("personaCSVItemReader")
				.resource(new FileSystemResource(fname)).linesToSkip(1).delimited()
				.names(new String[] { "id", "nombre", "apellidos", "correo", "sexo", "ip" })
				.fieldSetMapper(new BeanWrapperFieldSetMapper<PersonaDTO>() {
					{
						setTargetType(PersonaDTO.class);
					}
				}).build();
	}

	@Autowired
	public PersonaItemProcessor personaItemProcessor;

	@Bean
	@DependsOnDatabaseInitialization
	JdbcBatchItemWriter<Persona> personaDBItemWriter(DataSource dataSource) {
		return new JdbcBatchItemWriterBuilder<Persona>()
				.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
				.sql("INSERT INTO personas VALUES (:id,:nombre,:correo,:ip)").dataSource(dataSource).build();
	}

	Step importCSV2DBStep(int index, String file, JdbcBatchItemWriter<Persona> toDB) {
		return new StepBuilder("importCSV2DBStep" + index, jobRepository)
				.<PersonaDTO, Persona>chunk(10, transactionManager).reader(personaCSVItemReader(file))
				.processor(personaItemProcessor).writer(toDB).build();
	}

/*	@Bean
	Job personasJob(PersonasJobListener listener, JdbcBatchItemWriter<Persona> personaDBItemWriter) {
		return new JobBuilder("personasJob", jobRepository).incrementer(new RunIdIncrementer()).listener(listener)
				.start(importCSV2DBStep(1, "input/personas-1.csv", personaDBItemWriter)).build();
	}
*/
	@Bean
	JdbcCursorItemReader<Persona> personaDBItemReader(DataSource dataSource) {
		return new JdbcCursorItemReaderBuilder<Persona>().name("personaDBItemReader")
				.sql("SELECT id, nombre, correo, ip FROM personas").dataSource(dataSource)
				.rowMapper(new BeanPropertyRowMapper<>(Persona.class)).build();
	}

	@Bean
	FlatFileItemWriter<Persona> personaCSVItemWriter() {
		return new FlatFileItemWriterBuilder<Persona>().name("personaCSVItemWriter")
				.resource(new FileSystemResource("output/outputData.csv"))
				.lineAggregator(new DelimitedLineAggregator<Persona>() {
					{// anonymous class constructor
						setDelimiter(",");
						setFieldExtractor(new BeanWrapperFieldExtractor<Persona>() {
							{// anonymous class constructor
								setNames(new String[] { "id", "nombre", "correo", "ip" });
							}
						});
					}
				}).build();
	}

	@Bean
	Step exportDB2CSVStep(JdbcCursorItemReader<Persona> personaDBItemReader) {
		return new StepBuilder("exportDB2CSVStep", jobRepository).<Persona, Persona>chunk(100, transactionManager)
				.reader(personaDBItemReader).writer(personaCSVItemWriter()).build();
	}

	/*
	 * @Bean Job personasJob(PersonasJobListener listener,
	 * JdbcBatchItemWriter<Persona> personaDBItemWriter, Step exportDB2CSVStep) {
	 * return new JobBuilder("personasJob", jobRepository).incrementer(new
	 * RunIdIncrementer()).listener(listener) .start(importCSV2DBStep(1,
	 * "input/personas-1.csv", personaDBItemWriter)).next(exportDB2CSVStep).build();
	 * }
	 * 
	 */

	public StaxEventItemReader<PersonaDTO> personaXMLItemReader() {
		XStreamMarshaller marshaller = new XStreamMarshaller();
		Map<String, Class> aliases = new HashMap<>();
		aliases.put("Persona", PersonaDTO.class);
		marshaller.setAliases(aliases);
		marshaller.setTypePermissions(AnyTypePermission.ANY);
		return new StaxEventItemReaderBuilder<PersonaDTO>().name("personaXMLItemReader")
				.resource(new FileSystemResource("input/Personas.xml")).addFragmentRootElements("Persona")
				.unmarshaller(marshaller).build();
	}

	@Bean
	Step importXML2DBStep1(JdbcBatchItemWriter<Persona> personaDBItemWriter) {
		return new StepBuilder("importXML2DBStep1", jobRepository).<PersonaDTO, Persona>chunk(10, transactionManager)
				.reader(personaXMLItemReader()).processor(personaItemProcessor).writer(personaDBItemWriter).build();
	}

	public StaxEventItemWriter<Persona> personaXMLItemWriter() {
		XStreamMarshaller marshaller = new XStreamMarshaller();
		Map<String, Class> aliases = new HashMap<>();
		aliases.put("Persona", Persona.class);
		marshaller.setAliases(aliases);
		return new StaxEventItemWriterBuilder<Persona>().name("personaXMLItemWriter")
				.resource(new FileSystemResource("output/outputData.xml")).marshaller(marshaller)
				.rootTagName("Personas").overwriteOutput(true).build();
	}

	@Bean
	Step exportDB2XMLStep(JdbcCursorItemReader<Persona> personaDBItemReader) {
		return new StepBuilder("exportDB2XMLStep", jobRepository).<Persona, Persona>chunk(100, transactionManager)
				.reader(personaDBItemReader).writer(personaXMLItemWriter()).build();
	}

	@Bean
	Job personasJob(Step importXML2DBStep1, Step exportDB2XMLStep, Step exportDB2CSVStep) {
		return new JobBuilder("personasJob", jobRepository).incrementer(new RunIdIncrementer()).start(importXML2DBStep1)
				.next(exportDB2XMLStep).next(exportDB2CSVStep).build();
	}

}
