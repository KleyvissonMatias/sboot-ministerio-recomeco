SELECT
    id as id,
    nomeCompleto as nomeCompleto,
    cpf as cpf,
    dataNascimento as dataNascimento,
    sexo as sexo,
    estadoCivil as estadoCivil,
    telefone as telefone,
    telefoneOutroContato as telefoneOutroContato,
    endereco as endereco,
    email as email,
    redeSocial as redeSocial,
    possuiCelula as possuiCelula,
    nomeCelula as nomeCelula,
    tipoConversao as tipoConversao,
    campus as campus,
    status as status,
    culto as culto,
    horarioCulto as horarioCulto,
    observacao as observacao,
    liderCelula as liderCelula,
    liderTreinamento as liderTreinamento,
    dataBatismo as dataBatismo,
    dataEncontro as dataEncontro,
    dataRenovo as dataRenovo,
    dataAguias as dataAguias,
    dataLidereUm as dataLidereUm,
    dataLidereDois as dataLidereDois,
    dataInclusao as dataInclusao,
    dataAlteracao as dataAlteracao,
    dataExclusao as dataExclusao
FROM ministeriodb.vidas
WHERE status LIKE :status