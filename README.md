# Luma-TestAutomation

## Overview

This automation suite verifies the core **Payment & Checkout flow** of the web application using Selenium WebDriver and TestNG. It follows the **Page Object Model (POM)** design pattern and leverages **data-driven testing** to cover both positive and negative scenarios.

---

## ✅ Test Scenarios

| Test Case ID | Description |
|--------------|-------------|
| TC_003A | Add second item to cart and successfully complete checkout |
| TC_003B | Add first item to cart and successfully complete checkout |
| TC_003C | Add item to cart but fail checkout due to invalid phone number |

Each scenario includes full flow validation from adding items to the cart, navigating through the checkout process, filling shipping data, and asserting final outcomes.

---

## 🔧 Tech Stack

- **Language:** Java  
- **Framework:** TestNG  
- **Automation:** Selenium WebDriver  
- **Design Pattern:** Page Object Model (POM)  
- **Build Tool:** Maven / Gradle (assumed)  

---

## 📁 Project Structure

<details> <summary>Click to expand</summary>

```
src/
├── main/
│   └── java/
│       ├── Pages/
│       │   ├── BasePage.java
│       │   ├── CartPage.java
│       │   ├── CheckoutPage.java
│       │   ├── HomePage.java
│       │   └── SuccessPage.java
│       │
│       └── TestData/
│           ├── TestData.java
│           └── URLs.java
│
└── test/
    └── java/
        ├── Listeners/
        │   └── CustomListener.java
        │
        └── TestSuite/
            ├── BaseTest.java
            └── PaymentFlowTests.java
```
</details>


## 🚀 How to Run

1. **Clone the repository**  
   ```bash
   git clone https://github.com/your-username/your-repo.git

2. Open the project in your IDE
    Recommended: IntelliJ IDEA

3. Reload Maven dependencies
- Open the Maven tool window
- Click the "Reload Project" button
- This ensures all dependencies are downloaded and configured correctly.

4. Run the tests via TestNG
- Locate and right-click the testng.xml file
- Select Run 'testng.xml'

(Optional) View the HTML test report
After execution, open the following file in a browser to view the results: 
  ```
  test-output/html/index.html
