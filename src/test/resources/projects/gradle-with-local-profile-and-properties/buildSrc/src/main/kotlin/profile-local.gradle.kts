plugins {
  java
  // jhipster-needle-gradle-plugins
}

val javaVersion by extra("21")
// jhipster-needle-gradle-properties

dependencies {
  // Access to the `libs` object doesn't work in precompiled script plugin
  // https://github.com/gradle/gradle/issues/15383
  //testImplementation(libs.spring.boot.devtools)

  // jhipster-needle-gradle-implementation-dependencies
  // jhipster-needle-gradle-compile-dependencies
  // jhipster-needle-gradle-runtime-dependencies
  // jhipster-needle-gradle-test-dependencies
}
