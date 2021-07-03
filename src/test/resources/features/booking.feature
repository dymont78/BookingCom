Feature: Search on Booking.com

  Scenario: Search by city
    Given User is looking for hotels in "Minsk" city
    When User does search
    Then Hotel 'Apartland Griboedova' should be on the first line of search results page
    Then Hotel 'Apartland Griboedova' rating is '8,2'