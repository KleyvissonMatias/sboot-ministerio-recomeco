SELECT
    id as id,
    nomeCelula as nomeCelula,
    nomeLider as nomeLider,
    diaReuniao as diaReuniao,
    horario as horario,
    endereco as endereco,
    telefoneContato as telefoneContato,
    outroTelefone as outroTelefone,
    dataInclusao as dataInclusao,
    dataAlteracao as dataAlteracao,
    dataExclusao as dataExclusao
FROM ministeriodb.celulas
WHERE nomeLider LIKE :nomeLider;