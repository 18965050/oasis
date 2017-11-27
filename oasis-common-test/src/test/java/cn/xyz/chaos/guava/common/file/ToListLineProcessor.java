package cn.xyz.chaos.guava.common.file;

import java.io.IOException;
import java.util.List;

import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.io.LineProcessor;

/**
 *
 * @author lvchenggang
 *
 */
public class ToListLineProcessor implements LineProcessor<List<String>> {

    private static final Splitter splitter = Splitter.on("|");
    private List<String> bookTitles = Lists.newArrayList();
    private static final int TITLE_INDEX = 1;

    @Override
    public List<String> getResult() {
        return bookTitles;
    }

    @Override
    public boolean processLine(String line) throws IOException {
        bookTitles.add(Iterables.get(splitter.split(line), TITLE_INDEX));
        return true;
    }
}
