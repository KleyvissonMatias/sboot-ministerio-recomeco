UPDATE ministeriodb.vidas
SET
    status = :status,
    dataAlteracao = NOW()
WHERE cpf = :cpf;

