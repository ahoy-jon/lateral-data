# Lateral Data Miner / Scrapper

## Scrap

### Init the web miner

    WebMiner webMiner = new WebMiner("http://stuff.com/stuff.html");
    
    stuff.html contain : 
    
    &lt;html&gt;
     &lt;div&gt;&lt;h1&gt;Element 1&lt;/h1&gt;
      &lt;ul&gt;
       &lt;li class="scrap"&gt;Good !&lt;/li&gt;
       &lt;li&gt;Bad&lt;/li&gt;
       &lt;li class="scrap"&gt;Great !&lt;/li&gt;
       &lt;li&gt;Wrong&lt;/li&gt;
      &lt;/ul&gt;
     &lt;/div&gt;
     &lt;div&gt;
      &lt;ul&gt;
       &lt;li class="scrap"&gt;Good 2&lt;/li&gt;
       &lt;li&gt;Bad 2&lt;/li&gt;
       &lt;li&gt;Wrong 2&lt;/li&gt;
       &lt;li class="scrap"&gt;Great 2&lt;/li&gt;
      &lt;/ul&gt;
     &lt;/div&gt;
     &lt;div&gt;&lt;h1&gt;Element 3&lt;/h1&gt;
      &lt;ul&gt;
       &lt;li&gt;Bad 3&lt;/li&gt;
       &lt;li class="scrap"&gt;Good 3&lt;/li&gt;
       &lt;li&gt;Wrong 3 &lt;a href="http://link3"&gt;link 3&lt;/a&gt;&lt;/li&gt;
      &lt;/ul&gt;
     &lt;/div&gt;
    &lt;/html&gt;

### On a single list

    List<String> list = webMiner.extract("h1", "li.scrap");
    

"list" will contains : List("Element 1", "Element 3", "Good !", "Great !", "Good 2", "Great 2", "Good 3")

### On a multi list

    List<List<String>> list = webMiner.forEach("div").extract($("h1"), $("li.scrap"), $("a").attr("href"));

"list" will contains 3 List<String>:
0 : List("Element 1", "Good !", "Great !");
1 : List("Good 2", "Great 2");
2 : List("Element 3", "Good 3", "http://link3");


