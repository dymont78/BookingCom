Feature: Search on Booking.com

  Scenario: Search by city
    Given User is looking for hotels in "Minsk" city
    When User does search
    Then Hotel 'Apartment on Repina 4' should be on the first line of search results page
