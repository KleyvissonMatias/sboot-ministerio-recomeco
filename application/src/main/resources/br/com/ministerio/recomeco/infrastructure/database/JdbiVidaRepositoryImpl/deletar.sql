UPDATE ministeriodb.vida
SET
    dataExclusao = NOW()
WHERE  id = :id;
