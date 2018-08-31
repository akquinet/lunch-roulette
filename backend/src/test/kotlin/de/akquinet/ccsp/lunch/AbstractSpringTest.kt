package de.akquinet.ccsp.lunch

import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.transaction.support.TransactionTemplate

@RunWith(SpringRunner::class)
@SpringBootTest(classes = [(Application::class)],
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// Override JDBC parameters
@TestPropertySource(locations = ["classpath:application-test.properties"])
// Avoid LazyInitializationException
//@Transactional @Commit
abstract class AbstractSpringTest {
    @Autowired
    lateinit var txTemplate: TransactionTemplate
}
