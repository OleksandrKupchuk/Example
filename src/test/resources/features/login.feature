Feature: Login

  Scenario Outline: User success login
    When [UI] User login with username <username> and <password>
    Then [UI] User should be on main page <title>

    Examples:
      | username | password             | title       |
      | tomsmith | SuperSecretPassword! | Secure Area |
