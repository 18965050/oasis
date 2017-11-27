package cn.xyz.chaos.jakarta.common.xml;

import static org.hamcrest.CoreMatchers.is;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.ObjectCreateRule;
import org.apache.commons.digester.Rule;
import org.apache.commons.digester.RuleSetBase;
import org.apache.commons.digester.SetPropertiesRule;
import org.apache.commons.digester.substitution.MultiVariableExpander;
import org.apache.commons.digester.substitution.VariableSubstitutor;
import org.apache.commons.digester.xmlrules.DigesterLoader;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import cn.xyz.chaos.jakarta.common.model.Author;
import cn.xyz.chaos.jakarta.common.model.Book;
import cn.xyz.chaos.jakarta.common.model.Play;

/**
 * commons-digester 单元测试
 * <p />
 * digester是用于XML和JavaBean之间转换的工具
 *
 * @author lvchenggang
 *
 */
public class DigesterTest {

    /**
     * XML转换为JavaBean测试
     */
    @Test
    public void testXml2Object() throws Exception {
        List<Play> list = new ArrayList<Play>();
        InputStream input = getClass().getResourceAsStream("/jakarta/plays.xml");
        URL rules = getClass().getResource("/jakarta/play-rules.xml");
        Digester digester = DigesterLoader.createDigester(rules);
        digester.push(list);
        list = (List) digester.parse(input);
        Assert.assertThat(list.size(), is(1));
        Assert.assertThat(list.get(0).getCharacters().size(), is(3));
        Assert.assertThat(list.get(0).getName(), is("Hamlet"));
    }

    /**
     * 当XML拥有多个NameSpace的转换测试
     */
    @Test
    public void testMultiNamespace() throws Exception {
        class BookRuleSet extends RuleSetBase {
            public BookRuleSet() {
                this.namespaceURI = "http://cn.xyz.gaeo.jakarta.commons/book";
            }

            @Override
            public void addRuleInstances(Digester digester) {
                digester.addObjectCreate("*/book", Book.class);
                digester.addSetProperties("*/book");
            }
        }

        class AuthorRuleSet extends RuleSetBase {
            public AuthorRuleSet() {
                this.namespaceURI = "http://cn.xyz.gaeo.jakarta.commons/author";
            }

            @Override
            public void addRuleInstances(Digester digester) {
                digester.addObjectCreate("*/author", Author.class);
                digester.addSetNext("*/author", "setAuthor");
                digester.addSetProperties("*/author");
            }
        }
        Digester digester = new Digester();
        digester.setNamespaceAware(true);
        digester.addRuleSet(new BookRuleSet());
        digester.addRuleSet(new AuthorRuleSet());
        InputStream input = getClass().getResourceAsStream("/jakarta/book.xml");
        Book book = (Book) digester.parse(input);
        Assert.assertThat(book.getName(), is("How-to-be A Good Man"));
        Assert.assertThat(book.getAuthor().getName(), is("lcg"));
        Assert.assertThat(book.getAuthor().getFavoriteColor(), is("blue"));
    }

    /**
     * digester自定义rule测试
     */
    @Test
    public void testRule() throws Exception {
        class UpperRule extends Rule {
            @Override
            public void body(String namespace, String name, String text) throws Exception {
                Author author = (Author) digester.getRoot();
                author.setName(StringUtils.upperCase(author.getName()));

            }
        }
        ;

        class ReverseRule extends Rule {
            @Override
            public void body(String namespace, String name, String text) throws Exception {
                Author author = (Author) digester.getRoot();
                author.setName(StringUtils.reverse(author.getName()));
            }
        }
        ;

        InputStream input = getClass().getResourceAsStream("/jakarta/rule.xml");
        Digester digester = new Digester();
        digester.getRules().add("author", new ObjectCreateRule(Author.class));
        digester.getRules().add("author", new SetPropertiesRule());
        digester.addRule("*/upper", new UpperRule());
        digester.addRule("*/reverse", new ReverseRule());
        Author author = (Author) digester.parse(input);
        Assert.assertThat(author.getName(), is("GCL"));
    }

    /**
     * digester变量替换测试
     *
     * @throws Exception
     */
    @Test
    public void testVarSubstitute() throws Exception {
        Map varMap = new HashMap();
        varMap.put("name", "lcg");
        varMap.put("favoriteColor", "blue");
        MultiVariableExpander expander = new MultiVariableExpander();
        expander.addSource("$", varMap);
        InputStream input = getClass().getResourceAsStream("/jakarta/variable.xml");
        Digester digester = new Digester();
        digester.getRules().add("author", new ObjectCreateRule(Author.class));
        digester.getRules().add("author", new SetPropertiesRule());
        digester.setSubstitutor(new VariableSubstitutor(expander));
        Author author = (Author) digester.parse(input);
        Assert.assertThat(author.getName(), is("lcg"));
        Assert.assertThat(author.getFavoriteColor(), is("blue"));
    }

}
