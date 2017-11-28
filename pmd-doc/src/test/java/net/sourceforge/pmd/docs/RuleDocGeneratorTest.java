/**
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */

package net.sourceforge.pmd.docs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;

import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import net.sourceforge.pmd.RuleSet;
import net.sourceforge.pmd.RuleSetFactory;
import net.sourceforge.pmd.RuleSetNotFoundException;
import net.sourceforge.pmd.docs.MockedFileWriter.FileEntry;

public class RuleDocGeneratorTest {

    private MockedFileWriter writer = new MockedFileWriter();
    private Path root;

    @Before
    public void setup() throws IOException {
        writer.reset();

        root = Files.createTempDirectory("pmd-ruledocgenerator-test");
        Files.createDirectories(root.resolve("docs/_data/sidebars"));
        try (Writer out = Files.newBufferedWriter(root.resolve("docs/_data/sidebars/pmd_sidebar.yml"), StandardCharsets.UTF_8)) {
            IOUtils.write("entries:\n- title: sidebar\n  folders:\n  - title: 1\n  - title: 2\n  - title: Rules\n", out);
        }
    }

    @After
    public void cleanup() throws IOException {
        Files.walkFileTree(root, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Files.delete(file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                Files.delete(dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }

    @Test
    public void testSingleRuleset() throws RuleSetNotFoundException, IOException {
        RuleDocGenerator generator = new RuleDocGenerator(writer, root);

        RuleSetFactory rsf = new RuleSetFactory();
        RuleSet ruleset = rsf.createRuleSet("rulesets/ruledoctest/sample.xml");

        generator.generate(Arrays.asList(ruleset).iterator(),
                Arrays.asList(
                        "rulesets/ruledoctest/sample-deprecated.xml",
                        "rulesets/ruledoctest/other-ruleset.xml"));

        assertEquals(3, writer.getData().size());
        FileEntry languageIndex = writer.getData().get(0);
        assertTrue(languageIndex.getFilename().endsWith("docs/pages/pmd/rules/java.md"));
        assertEquals(IOUtils.toString(RuleDocGeneratorTest.class.getResourceAsStream("/expected/java.md")),
                languageIndex.getContent());

        FileEntry ruleSetIndex = writer.getData().get(1);
        assertTrue(ruleSetIndex.getFilename().endsWith("docs/pages/pmd/rules/java/sample.md"));
        assertEquals(IOUtils.toString(RuleDocGeneratorTest.class.getResourceAsStream("/expected/sample.md")),
                ruleSetIndex.getContent());

        FileEntry sidebar = writer.getData().get(2);
        assertTrue(sidebar.getFilename().endsWith("docs/_data/sidebars/pmd_sidebar.yml"));
        assertEquals(IOUtils.toString(RuleDocGeneratorTest.class.getResourceAsStream("/expected/pmd_sidebar.yml")),
                sidebar.getContent());
    }
}
