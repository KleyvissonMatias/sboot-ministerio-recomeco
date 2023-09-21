UPDATE ministeriodb.vida
SET
    status = :status,
    dataAlteracao = NOW()
WHERE cpf = :cpf;

