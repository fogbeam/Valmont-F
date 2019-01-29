## Overview
Valmont/F - A re-implementation of the classic [ArrowSmith](http://arrowsmith.psych.uic.edu/arrowsmith_uic/) [Literature Based Discovery](https://en.wikipedia.org/wiki/Literature-based_discovery) system, using Groovy and Grails.

This is a learning platform, for anyone interested in LBD, to play and experiment.  Our
first goal is to re-implement the classic algorithm(s) that [Don Swanson](https://en.wikipedia.org/wiki/Don_R._Swanson) developed, and then start looking for new and interesting variations and additions.  

As such, one of our primary goals here is to make this as modular and pluggable as possible, in
order to facilitate experimenting with novel approaches to Literature Based Discovery.

This is very much a Work In Progress at the moment, featuring fairly naive implementations of the orignal "procedure one" and "procedure two" approaches from Swanson's original A-B-C discovery algorithm.

## About

When I say I am called Valmont/F, the name will convey no impression to the reader, one way or another. My occupation is that of open source Literature Based Discovery system on GitHub. If you ask anyone who Valmont was, she will likely be able to tell you to see [http://www.gutenberg.org/files/19369/19369-h/19369-h.htm](http://www.gutenberg.org/files/19369/19369-h/19369-h.htm) If you ask here why I am named Valmont/F, she will surely say that I am named after the world-famous detective, Eugène Valmont.


## Reference Material

* [Arrowsmith Project at UIC](http://arrowsmith.psych.uic.edu/arrowsmith_uic/index.html)
* [An interactive system for finding complementary
literatures: a stimulus to scientific discovery ](https://ac.els-cdn.com/S0004370297000088/1-s2.0-S0004370297000088-main.pdf?_tid=197fe0f1-d912-478b-8534-37fcd447e2dc&acdnat=1547877235_4976ac1be0fd0c7164e0f18bb3aaa8d7)
* [Rediscovering Don Swanson:The Past, Present and Future of Literature-based Discovery](https://content.sciendo.com/view/journals/jdis/2/4/article-p43.xml)
* [Undiscovered Public Knowledge](http://arrowsmith.psych.uic.edu/arrowsmith_uic/tutorial/swanson_libquart_1986.pdf)
* [Literature Based Discovery: Beyond the ABCs](http://arrowsmith.psych.uic.edu/arrowsmith_uic/tutorial/smalheiser_2012.pdf)
* [Ranking Indirect Connections in Literature-Based
Discovery: The Role of Medical Subject Headings](http://arrowsmith.psych.uic.edu/arrowsmith_uic/tutorial/swanson_jasist_2006.pdf)
* [Literature Based Discovery: Models, Methods, and Trends](https://ac.els-cdn.com/S1532046417301909/1-s2.0-S1532046417301909-main.pdf?_tid=edb0bfb1-90e6-4027-a817-8817c67298d1&acdnat=1547877280_bb234971c849f259006502cbcb033420)
* [HTS and hit finding in academia – from chemical genomics to drug discovery](https://ac.els-cdn.com/S1359644609003213/1-s2.0-S1359644609003213-main.pdf?_tid=a80b7bd4-7b78-4b53-9819-7005ce1fcbb3&acdnat=1547877295_518ab0fdecdff59ef493985ffeeb58ff)
* [Conceptual biology, hypothesis discovery, and text mining: Swanson's legacy](https://bio-diglib.biomedcentral.com/track/pdf/10.1186/1742-5581-3-2)
* [Literature‐based Resurrection of Neglected Medical Discoveries](https://journals.uic.edu/ojs/index.php/jbdc/article/view/3515/2953)
* [Using Concepts in Literature-Based Discovery: Simulating Swanson’s Raynaud–Fish Oil and Migraine–Magnesium Discoveries](http://faculty.washington.edu/wpratt/NLP/Weeber-JASIST.pdf)
* [Swanson linking revisited: Accelerating literature-based discovery across domains using a conceptual influence graph](https://pdfs.semanticscholar.org/1f6c/303718a69f0a74b8dd85ba5dfaf4df08e18d.pdf)
* [Improving Knowledge Discovery in Document Collections through Combining Text Retrieval and Link Analysis Techniques ](https://www.researchgate.net/profile/Wei_Jin28/publication/4324221_Improving_Knowledge_Discovery_in_Document_Collections_through_Combining_Text_Retrieval_and_Link_Analysis_Techniques/links/54d422250cf2970e4e62c744.pdf)
* [Constructing an Associative Concept Space for Literature-based Discovery](https://pdfs.semanticscholar.org/72ee/cdb92a5db396fc2927819b46d5a30bf91cf7.pdf)
* [Recent Advances in Literature Based Discovery](http://citeseerx.ist.psu.edu/viewdoc/download?doi=10.1.1.77.6842&rep=rep1&type=pdf)

## License

Original code provided by Fogbeam Labs is licensed under the Apache License v2. Data files and supporting libraries may be under separate licenses. See LICENSE file for more details.

## TODO

* add more terms to the clinical-stopwords list
* add a "domain selector" to toggle what archive is queried and what stopword list(s) are employed
* better tokenization of abstracts and titles, so we don't, for example, treat 'Start' and 'Start.' as different tokens and generate each as a 'b term'
* use NLP, deep learning, etc. to do deeper semantic analysis of article text to find more meaningful connections that simple co-occurence of words
* improve code structure to create reusable components that simplify implementing new algorithms and approaches
* add input validation to existing controllers
* figure out a UI experience for "drilling down" further into the results we currently return, especially for "Procedure One"
* support more complex relationships, especially "multi-hop" ones that involve more than two concepts
* Add visualizations to help navigate / explore results. Maybe use dot / graphviz 
* Add caching to reduce the need for downloading documents all the time
* Add ability to filter the initial query(ies) by date range

## Resources and Stuff for Future Experimtation

* https://research.fb.com/fasttext/
* https://opennlp.apache.org/
* https://stanfordnlp.github.io/CoreNLP/
* https://github.com/openwims
* http://giraph.apache.org/
* http://www.graphviz.org/
* https://github.com/nidi3/graphviz-java
* https://d3js.org/
* http://visjs.org/
* http://sigmajs.org/
	