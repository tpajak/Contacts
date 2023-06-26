import org.hyperskill.hstest.stage.StageTest;
import org.hyperskill.hstest.testcase.CheckResult;
import org.hyperskill.hstest.testcase.TestCase;

import java.util.List;
import java.util.function.Function;


class TestClue {

    Function<String, CheckResult> callback;

    TestClue(Function<String, CheckResult> callback) {
        this.callback = callback;
    }
}


public class ContactsTest extends StageTest<TestClue> {

    private CheckResult splitActionsFeedback(int actualSize, String... actions) {
        if (actualSize != actions.length) {
            return CheckResult.wrong(String.format("This test should contain %d actions: %s. " +
                            "You should separate your actions with an empty line.",
                    actions.length,
                    String.join(", ", actions)));
        } else {
            return null;
        }
    }

    @Override
    public List<TestCase<TestClue>> generate() {
        return List.of (
            new TestCase<TestClue>()
                .setInput("exit")
                .setAttach(new TestClue(output -> {
                    output = output.strip().toLowerCase();
                    if (!output.contains("enter action")) {
                        return new CheckResult(false,
                            "No \"Enter action\" substring found in the output");
                    }
                    return CheckResult.correct();
                })),

            new TestCase<TestClue>()
                .setInput(
                    "count\n" +
                    "exit")
                .setAttach(new TestClue(output -> {
                    output = output.strip().toLowerCase();
                    if (!output.contains("0 records")) {
                        return new CheckResult(false,
                            "No \"0 records\" substring found in the output. " +
                                    "If you already have the database, try to delete it.");
                    }
                    return CheckResult.correct();
                })),

            new TestCase<TestClue>()
                .setInput(
                    "count\n" +
                    "edit\n" +
                    "exit")
                .setAttach(new TestClue(output -> {
                    output = output.strip().toLowerCase();
                    if (!output.contains("no records to edit")) {
                        return new CheckResult(false,
                            "No \"No records to edit\" substring found in the output");
                    }
                    return CheckResult.correct();
                })),

            new TestCase<TestClue>()
                .setInput(
                    "count\n" +
                    "remove\n" +
                    "exit")
                .setAttach(new TestClue(output -> {
                    output = output.strip().toLowerCase();
                    if (!output.contains("no records to remove")) {
                        return new CheckResult(false,
                            "No \"No records to remove\" substring found in the output");
                    }
                    return CheckResult.correct();
                })),

            new TestCase<TestClue>()
                .setInput(
                    "add\n" +
                        "person\n" +
                        "John\n" +
                        "Smith\n" +
                        "\n" +
                        "\n" +
                        "123 456 789\n" +
                    "count\n" +
                    "exit")
                .setAttach(new TestClue(output -> {
                    output = output.strip().toLowerCase();
                    if (output.contains("0 records")) {
                        return new CheckResult(false,
                            "Can't add the person");
                    }
                    return CheckResult.correct();
                })),

            new TestCase<TestClue>()
                .setInput(
                    "add\n" +
                        "person\n" +
                        "John\n" +
                        "Smith\n" +
                        "\n" +
                        "\n" +
                        "123 456 789\n" +
                    "info\n" +
                        "1\n" +
                    "exit")
                .setAttach(new TestClue(output -> {
                    String[] blocks = output.strip().split("(\\s*\\n\\s*){2,}");
                    var feedback = splitActionsFeedback(blocks.length, "add", "info", "exit");
                    if (feedback != null) return feedback;
                    
                    String infoBlock = blocks[1];
                    if (!infoBlock.contains("Name: John")
                        || !infoBlock.contains("Surname: Smith")
                        || !infoBlock.contains("Birth date: [no data]")
                        || !infoBlock.contains("Gender: [no data]")
                        || !infoBlock.contains("Number: 123 456 789")
                        || !infoBlock.contains("Time created:")
                        || !infoBlock.contains("Time last edit:")) {
                        return new CheckResult(false,
                            "Something wrong with printing user info");
                    }
                    return CheckResult.correct();
                })),

            new TestCase<TestClue>()
                .setInput(
                    "add\n" +
                        "organization\n" +
                        "Pizza Shop\n" +
                        "Wall St. 1\n" +
                        "+0 (123) 456-789-9999\n" +
                    "info\n" +
                        "1\n" +
                    "exit")
                .setAttach(new TestClue(output -> {
                    String[] blocks = output.strip().split("(\\s*\\n\\s*){2,}");
                    var feedback = splitActionsFeedback(blocks.length, "add", "info", "exit");
                    if (feedback != null) return feedback;
                    
                    String infoBlock = blocks[1];
                    if (!infoBlock.contains("Organization name: Pizza Shop")
                        || !infoBlock.contains("Address: Wall St. 1")
                        || !infoBlock.contains("Number: +0 (123) 456-789-9999")
                        || !infoBlock.contains("Time created:")
                        || !infoBlock.contains("Time last edit:")) {
                        return new CheckResult(false,
                            "Something wrong with printing organization info");
                    }
                    return CheckResult.correct();
                })),

            new TestCase<TestClue>()
                .setInput(
                    "add\n" +
                        "person\n" +
                        "John\n" +
                        "Smith\n" +
                        "\n" +
                        "\n" +
                        "123 456 789\n" +
                    "edit\n" +
                        "1\n" +
                        "gender\n" +
                        "M\n" +
                    "info\n" +
                        "1\n" +
                    "exit")
                .setAttach(new TestClue(output -> {
                    String[] blocks = output.strip().split("(\\s*\\n\\s*){2,}");
                    var feedback = splitActionsFeedback(blocks.length, "add", "edit", "info", "exit");
                    if (feedback != null) return feedback;
                    
                    String infoBlock = blocks[2];
                    if (!infoBlock.contains("Name: John")
                        || !infoBlock.contains("Surname: Smith")
                        || !infoBlock.contains("Birth date: [no data]")
                        || !infoBlock.contains("Gender: M")
                        || !infoBlock.contains("Number: 123 456 789")
                        || !infoBlock.contains("Time created:")
                        || !infoBlock.contains("Time last edit:")) {
                        return new CheckResult(false,
                            "Editing person gender is not working");
                    }
                    return CheckResult.correct();
                })),

            new TestCase<TestClue>()
                .setInput(
                    "add\n" +
                        "person\n" +
                        "John2\n" +
                        "Smith2\n" +
                        "\n" +
                        "\n" +
                        "123 456 789\n" +
                    "edit\n" +
                        "1\n" +
                        "name\n" +
                        "John3\n" +
                    "info\n" +
                        "1\n" +
                    "exit")
                .setAttach(new TestClue(output -> {
                    String[] blocks = output.strip().split("(\\s*\\n\\s*){2,}");
                    var feedback = splitActionsFeedback(blocks.length, "add", "edit", "info", "exit");
                    if (feedback != null) return feedback;
                    
                    String infoBlock = blocks[2];
                    if (!infoBlock.contains("Name: John3")
                        || !infoBlock.contains("Surname: Smith2")
                        || !infoBlock.contains("Birth date: [no data]")
                        || !infoBlock.contains("Gender: [no data]")
                        || !infoBlock.contains("Number: 123 456 789")
                        || !infoBlock.contains("Time created:")
                        || !infoBlock.contains("Time last edit:")) {
                        return new CheckResult(false,
                            "Editing person name is not working");
                    }
                    return CheckResult.correct();
                })),

            new TestCase<TestClue>()
                .setInput(
                    "add\n" +
                        "person\n" +
                        "John2\n" +
                        "Smith2\n" +
                        "\n" +
                        "\n" +
                        "123 456 789\n" +
                    "edit\n" +
                        "1\n" +
                        "number\n" +
                        "321 654 978\n" +
                    "info\n" +
                        "1\n" +
                    "exit")
                .setAttach(new TestClue(output -> {
                    String[] blocks = output.strip().split("(\\s*\\n\\s*){2,}");
                    var feedback = splitActionsFeedback(blocks.length, "add", "edit", "info", "exit");
                    if (feedback != null) return feedback;
                    
                    String infoBlock = blocks[2];
                    if (!infoBlock.contains("Name: John2")
                        || !infoBlock.contains("Surname: Smith2")
                        || !infoBlock.contains("Birth date: [no data]")
                        || !infoBlock.contains("Gender: [no data]")
                        || !infoBlock.contains("Number: 321 654 978")
                        || !infoBlock.contains("Time created:")
                        || !infoBlock.contains("Time last edit:")) {
                        return new CheckResult(false,
                            "Editing person number is not working");
                    }
                    return CheckResult.correct();
                })),

            new TestCase<TestClue>()
                .setInput(
                    "add\n" +
                        "organization\n" +
                        "Pizza Shop\n" +
                        "Wall St. 1\n" +
                        "+0 (123) 456-789-9999\n" +
                    "edit\n" +
                        "1\n" +
                        "address\n" +
                        "Wall St 2\n" +
                    "info\n" +
                        "1\n" +
                    "exit")
                .setAttach(new TestClue(output -> {
                    String[] blocks = output.strip().split("(\\s*\\n\\s*){2,}");
                    var feedback = splitActionsFeedback(blocks.length, "add", "edit", "info", "exit");
                    if (feedback != null) return feedback;
                    
                    String infoBlock = blocks[2];
                    if (!infoBlock.contains("Organization name: Pizza Shop")
                        || !infoBlock.contains("Address: Wall St 2")
                        || !infoBlock.contains("Number: +0 (123) 456-789-9999")
                        || !infoBlock.contains("Time created:")
                        || !infoBlock.contains("Time last edit:")) {
                        return new CheckResult(false,
                            "Editing organization address is not working");
                    }
                    return CheckResult.correct();
                })),

            new TestCase<TestClue>()
                .setInput(
                    "add\n" +
                        "organization\n" +
                        "Pizza Shop\n" +
                        "Wall St. 1\n" +
                        "+0 (123) 456-789-9999\n" +
                    "edit\n" +
                        "1\n" +
                        "number\n" +
                        "+0 (123) 456-789-12345\n" +
                    "info\n" +
                        "1\n" +
                    "exit")
                .setAttach(new TestClue(output -> {
                    String[] blocks = output.strip().split("(\\s*\\n\\s*){2,}");
                    var feedback = splitActionsFeedback(blocks.length, "add", "edit", "info", "exit");
                    if (feedback != null) return feedback;
                    
                    String infoBlock = blocks[2];
                    if (!infoBlock.contains("Organization name: Pizza Shop")
                        || !infoBlock.contains("Address: Wall St. 1")
                        || !infoBlock.contains("Number: +0 (123) 456-789-12345")
                        || !infoBlock.contains("Time created:")
                        || !infoBlock.contains("Time last edit:")) {
                        return new CheckResult(false,
                            "Editing organization number is not working");
                    }
                    return CheckResult.correct();
                }))
        );
    }

    @Override
    public CheckResult check(String reply, TestClue clue) {
        try {
            return clue.callback.apply(reply);
        }
        catch (Exception ex) {
            return new CheckResult(false, "Can't check the answer");
        }
    }
}
