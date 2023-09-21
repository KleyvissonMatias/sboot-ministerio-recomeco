UPDATE ministeriodb.celula
SET
    dataExclusao = NOW()
WHERE id = :id;
