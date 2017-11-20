# README #

Utility library for Java and JMonkeyEngine related projects.

## How to build ##

This library uses [gradle](https://gradle.org/gradle-download/) as build system, and comes with the gradle wrapper included.
So no prior installation on your computer is required! You can just use the `gradlew` or `gradlew.bat` executables in the root of the sources.

To get an overview of all gradle tasks you can use:
```
./gradlew tasks
```

To assemble and test the project, run:
```
./gradlew build
```

## How to use it ##
If you use a build tool like gradle or maven, then impstack-utils is available through JCenter.
This will automatically include any dependencies you need.

Example of including impstack-utils in a gradle build:
```
dependencies {
    compile "org.impstack:impstack-utils:0.1"
}
```
Don't forget to add [JCenter](https://dl.bintray.com/remyvd/impstack) to your repositories 
```
repositories {
    jcenter()
}
```
