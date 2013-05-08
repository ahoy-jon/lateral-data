package com.lateralthoughts.data.miner;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.Arrays.asList;

public class WebMiner {

    private final String url;

    public WebMiner(String url) {
        this.url = url;
    }

    public List<String> extract(String... selectors) throws IOException {
        return extract(this.url, Iterables.transform(asList(selectors), toSelector()));
    }

    public List<String> extract(TypedSelector... selectors) throws IOException {
        return extract(this.url, asList(selectors));
    }

    public List<String> extract(Iterable<TypedSelector> selectors) throws IOException {
        return extract(this.url, selectors);
    }

    public static List<String> extract(String url, TypedSelector... selectors) throws IOException {
        return extract(url, asList(selectors));
    }

    static Function<String, TypedSelector> toSelector() {
        return new Function<String, TypedSelector>() {
            @Override
            public TypedSelector apply(String cssQuery) {
                return TypedSelector.text(cssQuery);
            }
        };
    }

    public static List<String> extract(String url, Iterable<TypedSelector> selectors) throws IOException {
        return extract(Jsoup.connect(url).get(), selectors);
    }

    static List<String> extract(Element root, String... selectors) {
        return extract(root, Iterables.transform(asList(selectors), toSelector()));

    }

    static List<String> extract(Element root, Iterable<TypedSelector> selectors) {
        List<String> result = newArrayList();
        for (TypedSelector selector : selectors) {
            Elements elements = root.select(selector.query());
            for (Element element : elements) {
                result.add(selector.select(element));
            }
        }
        return result;
    }

    public ForEachSpecification forEach(String cssQuery) {
        return new ForEachSpecification(url, cssQuery);
    }
}
