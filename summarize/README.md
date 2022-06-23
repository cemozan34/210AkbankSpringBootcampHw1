# Summarize of a Maven project

```
Runinnig Method 1: 1- cd .\summarize\ (or the folder you want to test after pom.xml configuration)
                   2- mvn clean install
                   3- mvn com.akbank.hw1:summarize-maven-plugin:0.0.1-SNAPSHOT:summarize -DfileName="cemozan"

Running Method 2:  1- Right click on your project folder in the Project Explorer by using Eclipse
                   2- Run As -> Maven Build...
                   3- Enter clean install to the Goals part and click Run button
                   4- Open the same window specified in the first two steps, now enter com.akbank.hw1:summarize-maven-plugin:0.0.1-SNAPSHOT:summarize -DfileName="cemozan"

NOTE:   - For the -DfileName part you can enter any file name as you want. The code creates a txt file name with your choice and fills it 
        with summary of the project and locates it under the "target" folder.
        - If your Maven project does not have <developers> section code prints just ---Developers--- to console and the txt file. Also if your 
        project does not have a <release.date> property, code prints null for that section.
        - com.akabank.hw1 -> groupId, summarize-maven-plugin -> artifactId, 0.0.1-SNAPSHOT -> version, summarize -> Goal, DfileName -> parameter.

pom.xml plugin configuration for testing this custom summarize plugin in different projects:
        <plugin>
            <groupId>com.akbank.hw1</groupId>
            <artifactId>summarize-maven-plugin</artifactId>
            <version>0.0.1-SNAPSHOT</version>
            <executions>
                  <execution>
                          <goals>
                          <goal>summarize</goal>
                          </goals>
                  </execution>
            </executions>
        </plugin>

Running Method for other projects same as the first two running methods that I mentioned above. Just change the cd .\summarize\ to the your folder name.

``` 
