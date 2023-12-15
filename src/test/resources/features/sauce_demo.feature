Feature: SauceDemo feature

  Scenario: User log in to Sauce demo page
    Given user open main page
    When user logs in with credentials: "standard_user" and "secret_sauce"
    Then user is authenticated

  Scenario Outline: User get error messages
    Given user open main page
    When user logs in with credentials: "<login>" and "<password>"
    Then user see error message: "<error_message>"
    Examples:
      | login              | password             | error_message                                                             |
      |                    |                      | Epic sadface: Username is required                                        |
      |                    | secret_sauce         | Epic sadface: Username is required                                        |
      | standard_user      |                      | Epic sadface: Password is required                                        |
      | standard_user      | secret_fake_password | Epic sadface: Username and password do not match any user in this service |
      | standard_user_fake | secret_sauce         | Epic sadface: Username and password do not match any user in this service |

  Scenario: User added some items
    Given user open main page
    When user logs in with credentials: "standard_user" and "secret_sauce"
    Then user is authenticated
    Then user take Items
    Then user check item storage: User Added 6 Items
    Then user see his items:"Sauce Labs Backpack" "Sauce Labs Bike Light" "Sauce Labs Bolt T-Shirt" "Sauce Labs Fleece Jacket" "Sauce Labs Onesie" "T-Shirt (Red)"

  Scenario: User successfully finish ordering
    Given user open main page
    When user logs in with credentials: "standard_user" and "secret_sauce"
    Then user is authenticated
    Then user take Items
    Then user check item storage: User Added 6 Items
    Then user see his items:"Sauce Labs Backpack" "Sauce Labs Bike Light" "Sauce Labs Bolt T-Shirt" "Sauce Labs Fleece Jacket" "Sauce Labs Onesie" "T-Shirt (Red)"
    Then user select CheckOut Button
    Then user input delivery information:"Marek","Larcovich","LV-1520"
    Then user click Continue button
    Then user see his items:"Sauce Labs Backpack" "Sauce Labs Bike Light" "Sauce Labs Bolt T-Shirt" "Sauce Labs Fleece Jacket" "Sauce Labs Onesie" "T-Shirt (Red)"
    Then user click Finish button
    Then user see message in middle of page:"Thank you for your order!","Your order has been dispatched, and will arrive just as fast as the pony can get there!"




  Scenario Outline: User get error messages when input wrong delivery information
    Given user open main page
    When user logs in with credentials: "standard_user" and "secret_sauce"
    Then user is authenticated
    Then user take Items
    Then user check item storage: User Added 6 Items
    Then user select CheckOut Button
    Then user make mistake with check out fields: "<Name>","<Last_Name>","<Postal_Code>"
    Then user see check out error message:"<Error Message>"
    Examples:
      | Name  | Last_Name | Postal_Code | Error Message                  |
      |       |           |             | Error: First Name is required  |
      |       | Larcovich | LV-1520     | Error: First Name is required  |
      | Marek |           | LV-1520     | Error: Last Name is required   |
      | Marek | Larcovich |             | Error: Postal Code is required |



