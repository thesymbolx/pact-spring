package com.dale.pact_spring

import au.com.dius.pact.provider.junit5.PactVerificationContext
import au.com.dius.pact.provider.junitsupport.Provider
import au.com.dius.pact.provider.junitsupport.loader.PactFolder
import au.com.dius.pact.provider.spring.spring6.PactVerificationSpring6Provider
import org.junit.jupiter.api.TestTemplate
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import au.com.dius.pact.provider.junitsupport.State
import au.com.dius.pact.provider.junitsupport.loader.PactBroker
import au.com.dius.pact.provider.junitsupport.loader.PactBrokerAuth
import org.springframework.beans.factory.annotation.Autowired


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Provider("disney_api")
//@PactFolder("src/test/resources/pacts")
@PactBroker(
	url = "https://yorkshire-building-society.pactflow.io",
	enablePendingPacts = "true",
	providerBranch = "main",
	authentication = PactBrokerAuth(token = "k0xg2l3mRoGHrCo0vAPWUQ")
)
class PactSpringApplicationTests {

	@Autowired
	lateinit var repository: DisneyRepository

	@TestTemplate
	@ExtendWith(PactVerificationSpring6Provider::class)
	fun pactVerificationTestTemplate(context: PactVerificationContext) {
		context.verifyInteraction()
	}

	@State("a list of Disney characters exists")
	fun setupCharacterList() {
		repository.save(
			DisneyCharacter(112, "Mickey Mouse", "https://example.com/mickey.png"),
			DisneyCharacter(113, "Donald Duck", "https://example.com/donald.png"),
			DisneyCharacter(114, "Goofy", "https://example.com/goofy.png")
		)
	}

	@State("a disney character with id 112 exists")
	fun setupSingleCharacter() {
		repository.save(
			DisneyCharacter(112, "Mickey Mouse", "https://example.com/mickey.png")
		)
	}

}
