# oneBibleTranslation

Legacy Java web application that serves a single-translation Bible reading/search page per language.

The app is a Maven WAR project using JSP + Servlet API. It lists available translations on the home page and routes each translation URL to a servlet that renders a language-specific HTML template.

Each template can be downloaded as a .html file which stores the Bible locally.  You can search it using the local search system and jump directly to book, chapter, and verse.

## What This App Does

- Serves a home page at `index.jsp` with links for many Bible translations.
- Maps each language name to a database table/column key.
- Routes URLs like `/Spanish` (or other language names) through a filter to `/oneTran?lang=<language>`.
- Renders a translation page from an HTML template (`src/main/java/asst/one/index.html`) with placeholders replaced:
  - `__LANG__` -> selected language name
  - `__COL_KEY__` -> mapped table/column key
- Front-end JavaScript supports:
  - passage search with autosuggest
  - optional parallel English verses
  - requesting a downloadable searchable HTML version

## Important Runtime Dependency

This project depends on a separate companion web app named `corpus`.

The generated translation page calls endpoints under `../corpus`, including:
- `getRef.jsp` for verse lookup
- `ProduceHtml` for downloadable HTML output

Without the companion `corpus` app deployed and configured, search/download features will not work.

## Tech Stack

- Java 8 source/target
- Maven WAR packaging
- Servlet API 3.1 (`provided`)
- JSP
- MySQL connector (`mysql-connector-java:5.1.37`)
- Bootstrap + jQuery in `WebContent`

## Project Layout

- `pom.xml` - Maven build config (WAR artifact name set to `1.war`)
- `src/main/java/asst/MapLanguagesToTables.java` - language -> table/column map
- `src/main/java/asst/OilFilter.java` - URL filter that forwards language paths
- `src/main/java/asst/one/OneTranslation.java` - servlet that renders language page
- `src/main/java/asst/Utils.java` - template loading and string replacement helpers
- `src/main/java/asst/one/index.html` - HTML template for language pages
- `WebContent/index.jsp` - home page
- `WebContent/WEB-INF/web.xml` - web app descriptor and welcome file

## Build

From the project root:

```bash
mvn clean package
```

Output WAR:

- `target/1.war`

## Deploy

Deploy `target/1.war` to a servlet container (for example Apache Tomcat 8.5/9).

If deployed with context path `1`, the home page is typically:

- `http://localhost:8080/1/`

Also deploy the companion `corpus` app so relative calls to `../corpus/...` resolve correctly.

## Request Flow

1. User opens home page (`index.jsp`) and clicks a language link.
2. `OilFilter` intercepts path `/<language>`.
3. If language exists in `MapLanguagesToTables`, request is forwarded to `oneTran?lang=<language>`.
4. `OneTranslation` loads the HTML template and injects language + column key.
5. Browser executes JavaScript to query companion corpus endpoints.

## Notes and Caveats

- Language links include spaces/special characters, so browser URL encoding behavior matters.
- The map in `MapLanguagesToTables` appears shared with another project (comment notes this), so keep both in sync if edited.
- `WebContent/index.jsp` includes an old Google Analytics snippet (`UA-*`). Replace/remove as needed.
- This is a legacy codebase (Servlet/JSP style); no modern frontend build pipeline is required.

## Development Tips

- Use a local Tomcat run config in your IDE for easiest iteration.
- Rebuild WAR after Java/JSP/template changes:

```bash
mvn clean package
```

- Verify a language route after deploy, for example:

```text
http://localhost:8080/1/Spanish
```

If that route loads but search results do not, check whether the `corpus` app is running and reachable.
