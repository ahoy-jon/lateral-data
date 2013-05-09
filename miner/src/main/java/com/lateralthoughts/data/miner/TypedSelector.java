package com.lateralthoughts.data.miner;


import org.jsoup.nodes.Element;

public abstract class TypedSelector {

    private final String cssQuery;

    public abstract String select(Element element);

    public TypedSelector(String cssQuery) {
        this.cssQuery = cssQuery;
    }

    public String query() {
        return cssQuery;
    }

    public TypedSelector attr(final String attributeName) {
        return new TypedSelector(query()){
            @Override
            public String select(Element element) {
                return element.attr(attributeName);
            }
        };
    }

    public static TypedSelector text(final String cssQuery) {
        return new TypedSelector(cssQuery){

            @Override
            public String select(Element element) {
                return element.text();
            }
        };
    }

    public static TypedSelector attr(final String attributeName, final String cssQuery) {
        return new TypedSelector(cssQuery){
            @Override
            public String select(Element element) {
                return element.attr(attributeName);
            }
        };
    }

    public static TypedSelector $(final String cssQuery) {
        return new TypedSelector(cssQuery){
            @Override
            public String select(Element element) {
                return element.text();
            }
        };
    }
}
