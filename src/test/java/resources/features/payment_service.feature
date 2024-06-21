Feature: Payment Service

  Scenario: Create a new payment
    Given a customer payload with orderId "123456"
    When the client requests to create a payment
    Then the response should contain the payment's ID and details