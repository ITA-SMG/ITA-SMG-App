<br/>
<div align="center">
  <img src="docs/images/smg_logo.png" height="100px"/>
</div>
<br/>
<div align="center">
  <img src="docs/images/itechart_logo.png" height="30px"/>
</div>
<br/>
<div align="center">
  <h2>U5.JS Solution Management Gate</h2>
</div>

SMG App - Android/iOS app based on Kotlin Multiplatform, built with modern frameworks and approaches, such as Jetpack Compose and Decompose, Kodein, Apollo GraphQL, UIKit

## Requirements
Android:
- Android Studio Arctic Fox 2020.3 or higher
- JDK 11
- Kotlin 1.6.10 and compatible KMM plugin

iOS:
- All Android requirements
- Compatible version of XCode build tools (12.5 is fine)
- Cocoapods


## Useful scripts
    ./gradlew clean //Clean project
    ./gradlew lint //Static code check for errors and style issues

## Download GraphQL schema & Codegen
  To generate GraphQL schema, run:

    ./gradlew downloadApolloSchema \
    --endpoint="https://smg-graphql-proxy.herokuapp.com/graphql" \
    --schema="shared/src/commonMain/graphql/schema.graphqls"

  After adding a new query/mutation declaration near schema.graphqls file and running build,
  Apollo GraphQL plugin will generate data types based on operation properties
  
## Contributing
Create a PR using [this](docs/pull_request_template.md) template, wait for approvals from other devs & checkers, if all is fine - you're ready to merge!

## Links
- [Bitbucket Jira](https://itechart-smg.atlassian.net) - Jira page with project tasks
- [Bitbucket Confluence](https://itechart-smg.atlassian.net/wiki/spaces/SMG/pages/65785/SMG+App+Architecture) - Documentation pages
- [Bitrise Android](https://app.bitrise.io/app/f005ca26c0884ad6#/builds) - Bitrise CI/CD Android project
- [Bitrise iOS](https://app.bitrise.io/app/39af897e63f33a72#/builds ) - Bitrise CI/CD iOS project
- [Codacy](https://app.codacy.com/gh/TheLonelyAstronaut/ITA-SMG-App/dashboard?branch=dev) - Codecheck project
- [Proxy](https://github.com/TheLonelyAstronaut/ITA-SMG-Proxy) - Proxy project for SMG API
- [Staging](https://github.com/TheLonelyAstronaut/ITA-SMG-Mock-API) - Mock of SMG API for staging
- [Design](https://www.figma.com/file/eEsJ0WJqG5xRczdYtMr7BG/iTA-SMG-Mobile) - Figma project (Work in Progress)