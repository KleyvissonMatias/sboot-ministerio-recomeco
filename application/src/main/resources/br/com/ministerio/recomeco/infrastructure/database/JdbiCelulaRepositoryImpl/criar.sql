INSERT INTO ministeriodb.celulas
(id, nomeCelula, nomeLider, diaReuniao, horario, endereco, telefoneContato, outroTelefone, dataInclusao, dataAlteracao, dataExclusao)
VALUES(:id, :nomeCelula, :nomeLider, :diaReuniao, :horario, :endereco, :telefoneContato, :outroTelefone, NOW(), :dataAlteracao, :dataExclusao);
