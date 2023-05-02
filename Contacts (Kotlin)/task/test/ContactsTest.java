import org.hyperskill.hstest.stage.StageTest;
import org.hyperskill.hstest.testcase.CheckResult;
import org.hyperskill.hstest.testcase.TestCase;

import java.util.List;


class TestClue {

    String feedback;

    TestClue(String feedback, String outFile, Double[] answers) {

    }
}


public class ContactsTest extends StageTest<TestClue> {

    @Override
    public List<TestCase<TestClue>> generate() {
        return List.of(
            new TestCase<TestClue>()
                .setInput("John\nSmith\n1-234-567-890")
        );
    }

    @Override
    public CheckResult check(String reply, TestClue clue) {
        reply = reply.toLowerCase();
        if (!reply.contains("enter the name")
            && !reply.contains("enter a name")) {

            return new CheckResult(false,
                "I don't see a place to enter the name.");
        }
        if (!reply.contains("enter the surname")
            && !reply.contains("enter a surname")) {

            return new CheckResult(false,
                "I don't see a place to enter the surname.");
        }
        if (!reply.contains("enter the number")
            && !reply.contains("enter a number")) {

            return new CheckResult(false,
                "I don't see a place to enter the number.");
        }
        return CheckResult.correct();
    }
}
