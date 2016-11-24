package com.patientping;

import com.patientping.domain.Event;
import com.patientping.domain.EventRepository;
import com.patientping.domain.User;
import com.patientping.domain.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AdmitoneApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdmitoneApplication.class, args);
	}

	@Bean
	CommandLineRunner init(final UserRepository userRepository, final EventRepository eventRepository) {

		return new CommandLineRunner() {

			@Override
			public void run(String... arg0) throws Exception {
				if(userRepository.findByUserName("jeid")==null) {
					userRepository.save(new User("jeid", "password"));
				}
				if(!eventRepository.findAll().iterator().hasNext()) {
					eventRepository.save(new Event("Bassnectar"));
					eventRepository.save(new Event("Beethoven"));
				}
			}

		};

	}

}
