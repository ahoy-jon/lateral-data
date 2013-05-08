package com.lateralthoughts.data.miner;

import com.google.common.collect.Iterables;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static com.lateralthoughts.data.miner.WebMiner.toSelector;
import static java.util.Arrays.asList;

public class ForEachSpecification {

    final String url;
    final String cssQuery;

    public ForEachSpecification(String url, String cssQuery) {
        this.url = url;
        this.cssQuery = cssQuery;
    }

    public List<List<String>> extract(String... selectors) throws IOException {
        return extract(Iterables.transform(asList(selectors), toSelector()));
    }

    public List<List<String>> extract(TypedSelector... selectors) throws IOException {
        return extract(asList(selectors));
    }

    public List<List<String>> extract(Iterable<TypedSelector> selectors) throws IOException {
        Document doc = Jsoup.connect(url).get();
        List<List<String>> result = newArrayList();
        Elements elements = doc.select(cssQuery);
        for (Element element : elements) {
            result.add(WebMiner.extract(element, selectors));
        }

        return result;
    }

}
