Feature: Selecionar Passagem
  Scenario: Selecionar Passagem com Sucesso
    Given que acesso o site Blazedemo
    When soleciono a origem como "São Paolo" e destino "Berlin"
    And clico em Procurar Voo
    Then exibe a frase indicando voo entre "São Paolo" e "Berlin"