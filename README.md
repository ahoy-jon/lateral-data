# Lateral Data

## Miner / Scrapper

WebMiner is based on JSoup : http://jsoup.org/

### Init the web miner

    WebMiner webMiner = new WebMiner("http://stuff.com/stuff.html");
    
stuff.html contains : 
    
    <html>
     <div><h1>Element 1</h1>
      <ul>
       <li class="scrap">Good !</li>
       <li>Bad</li>
       <li class="scrap">Great !</li>
       <li>Wrong</li>
      </ul>
     </div>
     <div>
      <ul>
       <li class="scrap">Good 2</li>
       <li>Bad 2</li>
       <li>Wrong 2</li>
       <li class="scrap">Great 2</li>
      </ul>
     </div>
     <div><h1>Element 3</h1>
      <ul>
       <li>Bad 3</li>
       <li class="scrap">Good 3</li>
       <li>Wrong 3 <a href="http://link3">link 3</a></li>
      </ul>
     </div>
    </html>

### On a single list

    List<String> list = webMiner.extract("h1", "li.scrap");
    

"list" will contains : "Element 1", "Element 3", "Good !", "Great !", "Good 2", "Great 2", "Good 3"

### On a multi list

    List<List<String>> lists = webMiner.forEach("div").extract($("h1"), $("li.scrap"), $("a").attr("href"));

"lists" will contains 3 Lists :

*  0 : "Element 1", "Good !", "Great !"
*  1 : "Good 2", "Great 2"
*  2 : "Element 3", "Good 3", "http://link3"




