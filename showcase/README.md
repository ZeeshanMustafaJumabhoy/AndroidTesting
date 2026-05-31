# Project Showcase

Animated portfolio showcases for three automation stacks:

| Showcase | Stack | Target |
|----------|-------|--------|
| `Playwright-e2e.html` | Playwright · TypeScript | VIDIZMO web |
| `selenium-e2e-showcase.html` | Selenium · C# · MSTest | Advantage e-commerce |
| **`appium-mobile-showcase.html`** | **Appium · Java · TestNG** | **Toucher Android app** |

## View the Appium / mobile showcase

```bash
open showcase/appium-mobile-showcase.html
```

Or from the `showcase/` folder:

```bash
open appium-mobile-showcase.html
```

## Mobile assets (`assets/`)

| File | Description |
|------|-------------|
| `android-project-structure.png` | Maven / POM layout in IDE |
| `allure-dashboard.png` | Allure Report — SmokeTesting suite |
| `browserstack-dashboard.png` | BrowserStack Test Reporting & Analytics |

## What the mobile slideshow covers

1. **Intro** — Appium × Toucher (bowls scoring), TestNG, BrowserStack, Allure  
2. **Framework** — `LoginPage`, `ScorePage`, `AddUserPage`, `DriverFactory`  
3. **Login flow** — Animated Sign In mock + `LoginPage.login()` POM  
4. **Score flow** — Rounds, scores, WE/They members + `AppTest` smoke steps  
5. **BrowserStack** — Cloud run dashboard  
6. **Test results** — 7-case SmokeTesting suite (3 pass / 4 fail) + Allure  
7. **Allure** — Full report overview  
8. **Outro** — GitHub repo link  

## Run tests locally

```bash
# Local Appium (device + server on :4723)
mvn test

# BrowserStack (set BROWSERSTACK_USERNAME / BROWSERSTACK_ACCESS_KEY)
mvn test -Pbrowserstack
```

Generate Allure report after a run:

```bash
allure serve allure-results
```

## Tech highlighted (AndroidTesting)

- Appium Java Client · UiAutomator2 · Android  
- Page Object Model · Maven Surefire · TestNG  
- Allure TestNG listener · BrowserStack App Automate  
- Touch gestures (`performSwipe`) · dynamic UiSelector scoring  

## Other showcases

- **`selenium-e2e-showcase.html`** — Advantage shopping + GitHub Actions  
- **`Playwright-e2e.html`** — VIDIZMO reference template  
