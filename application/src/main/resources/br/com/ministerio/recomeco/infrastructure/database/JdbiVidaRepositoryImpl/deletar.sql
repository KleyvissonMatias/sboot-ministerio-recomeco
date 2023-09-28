UPDATE ministeriodb.vidas
SET
    dataExclusao = NOW()
WHERE  id = :id;
