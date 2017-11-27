package cn.xyz.chaos.jakarta.common.application;

import junit.framework.Assert;

import org.apache.commons.cli.AlreadySelectedException;
import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.MissingArgumentException;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.OptionGroup;
import org.apache.commons.cli.Options;
import org.junit.Test;

/**
 * commons-cli 单元测试
 * <p />
 * cli(Command-Line Interface)对命令行参数进行解析
 *
 * @author lvchenggang
 *
 */
public class CliTest {

    /**
     * {@link org.apache.commons.cli.Options}, <br />
     * {@link org.apache.commons.cli.CommandLineParser}测试
     *
     * @throws Exception
     */
    @Test(expected = MissingArgumentException.class)
    public void testBasicCli() throws Exception {
        CommandLineParser parser = new BasicParser();
        Options options = new Options();
        options.addOption("h", "help", false, "Print this usage information");
        options.addOption("v", "verbose", false, "Print out VERBOSE debugging information");
        options.addOption("f", "file", true, "File to save program output to");

        CommandLine commandLine = parser.parse(options, new String[] { "-h", "-f" });
        Assert.assertTrue(commandLine.hasOption("h"));

    }

    /**
     * {@link org.apache.commons.cli.OptionGroup}测试
     *
     * @throws Exception
     */
    @Test(expected = AlreadySelectedException.class)
    public void testComplexCli() throws Exception {
        CommandLineParser parser = new BasicParser();
        Options options = new Options();
        OptionGroup optionGroup = new OptionGroup();
        optionGroup.addOption(OptionBuilder.hasArg(true).create('f'));
        optionGroup.addOption(OptionBuilder.hasArg(true).create('m'));
        options.addOptionGroup(optionGroup);
        CommandLine commandLine = parser.parse(options, new String[] { "-f", "aa.txt", "-m", "abc@xyz.cn" });

    }

    /**
     * {@link org.apache.commons.cli.HelpFormatter}测试
     */
    @Test
    public void testPrintUsage() {
        String USAGE = "[-h] [-v] [-f <file> | -m <email>]";
        String HEADER = "SomeApp - A fancy and expensive program, Copyright 2010 XYZ.";
        String FOOTER = "For more instructions, see our website at: http://www.xyz.cn";
        CommandLineParser parser = new BasicParser();

        Options options = new Options();
        options.addOption("h", "help", false, "Print this usage information");
        options.addOption("v", "verbose", false, "Print out VERBOSE information");
        OptionGroup optionGroup = new OptionGroup();
        optionGroup.addOption(OptionBuilder.hasArg(true).withArgName("file").withLongOpt("file").create('f'));
        optionGroup.addOption(OptionBuilder.hasArg(true).withArgName("email").withLongOpt("email").create('m'));
        options.addOptionGroup(optionGroup);
        try {
            CommandLine commandLine = parser.parse(options, new String[] { "-f" });
        } catch (Exception e) {
            HelpFormatter helpFormatter = new HelpFormatter();
            helpFormatter.setWidth(80);
            helpFormatter.printHelp(USAGE, HEADER, options, FOOTER);

        }

    }

}
