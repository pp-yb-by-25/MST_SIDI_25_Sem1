Upgrade to Java 21 (LTS)

Summary
- This repository does not contain Maven or Gradle build files (pom.xml or build.gradle) so there is no centralized build to change. It appears to be an educational collection of Java source files.

What I did
- Installed JDK 21 to: C:\Users\parco\.jdk\jdk-21.0.8\bin

How to use Java 21 locally
1. Set JAVA_HOME to the JDK 21 installation. In PowerShell:

   $env:JAVA_HOME = 'C:\Users\parco\.jdk\jdk-21.0.8'
   $env:Path = "$env:JAVA_HOME\\bin;$env:Path"

2. Verify:

   java -version

IDE configuration
- In VS Code, install the 'Extension Pack for Java' and set the 'java.home' setting to the JDK path or configure the Java Tooling to use the installed JDK.
- In IntelliJ IDEA, open File > Project Structure > SDKs and add the JDK at C:\Users\parco\.jdk\jdk-21.0.8

Notes
- If you later add a build system (Maven/Gradle), we can run automated upgrade tooling to update source/target compatibility and plugin versions.
- If you want, I can also update individual source files to use Java 21 features or run a compiler over the files to check for compatibility issues.
