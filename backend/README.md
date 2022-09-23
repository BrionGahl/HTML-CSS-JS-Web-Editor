## Getting Started and Setup

Running the project requires Maven to be installed. To do so navigate to the following link: `https://maven.apache.org/download.cgi`
Download the .zip or tar.gz file, from here open the compressed file, and extract it to wherever you want Maven to be installed at. Then add it to your PATH environment variable pointing to the bin file inside, it should look something like this - `D:\Program Files\apache-maven-3.8.6\bin` inside the PATH variable. From here close all terminals and open a new one and type `mvn --version` to ensure everything is setup. Next run the below command inside the backend directory to setup the dependencies for the project. If you are using IntelliJ you can also navigate to the Maven section on the right and click on the lifecycle dropdown and run install there.

```bash
mvn install
```

You should now be good to run the development server. By just running the `EditorApplication.java` file via an idea of your choice. You won't be able to compile until I write up the build configuration but I don't want to that until we know what tools we will use besides Spring.
