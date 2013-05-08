package com.lateralthoughts.data.miner;

import com.google.common.io.Resources;
import com.google.common.util.concurrent.AbstractService;
import com.lateralthoughts.stub.HttpServerStub;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static com.lateralthoughts.data.miner.TypedSelector.$;
import static org.fest.assertions.Assertions.assertThat;

public class WebMinerTest {

    final static int STUB_PORT = 8086;
    final static String STUBED_URL = "http://localhost:" + STUB_PORT + "/";
    static AbstractService server;

    @BeforeClass
    public static void beforeClass() {
        server = new HttpServerStub(STUB_PORT, Resources.getResource("WebMock.properties").getPath());
        server.startAndWait();
    }

    @AfterClass
    public static void afterClass() {
        server.stopAndWait();
    }

    @Test
    public void shouldMineSimplestWay() throws IOException {
        WebMiner webMiner = new WebMiner(STUBED_URL);

        List<String> result = webMiner.extract("h1", "li.scrap");

        assertThat(result).hasSize(7);
        assertThat(result).isEqualTo(newArrayList("Element 1", "Element 3", "Good !", "Great !", "Good 2", "Great 2", "Good 3"));
    }

    @Test
    public void shouldMineMultipleLine() throws IOException {
        WebMiner webMiner = new WebMiner(STUBED_URL);
        List<List<String>> result = webMiner.forEach("div").extract($("h1"), $("li.scrap"), $("a").attr("href"));

        assertThat(result).hasSize(3);
        assertThat(result.get(0)).containsExactly("Element 1", "Good !", "Great !");
        assertThat(result.get(1)).containsExactly("Good 2", "Great 2");
        assertThat(result.get(2)).containsExactly("Element 3", "Good 3", "http://link3");
    }
}
