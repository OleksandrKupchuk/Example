@regression
Feature: Logout

  Scenario Outline: User success logout
    Given [UI] User login with username <username> and <password>
    And [UI] User should be on main page <mainTitle>
    When [UI] User click on logout button
    Then [UI] User should be on login page <loginTitle>

    Examples:
      | username | password             | mainTitle   | loginTitle |
      | tomsmith | SuperSecretPassword! | Secure Area | Login Page |
      | tomsmith | SuperSecretPassword! | Secure Area | Login Page |
