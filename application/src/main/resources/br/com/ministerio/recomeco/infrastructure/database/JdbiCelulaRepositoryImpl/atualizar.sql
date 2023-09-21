UPDATE ministeriodb.celula
SET
    nomeCelula = :nomeCelula,
    nomeLider = :nomeLider,
    diaReuniao = :diaReuniao,
    horario = :horario,
    endereco = :endereco,
    telefoneContato = :telefoneContato,
    outroTelefone = :outroTelefone,
    dataInclusao = :dataInclusao,
    dataAlteracao = NOW(),
    dataExclusao = :dataExclusao
WHERE id = :id;
