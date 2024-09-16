CREATE TABLE log (
                                  id UUID PRIMARY KEY,
                                  method_name VARCHAR NOT NULL,
                                  method_type VARCHAR NOT NULL,
                                  execution_time BIGINT NOT NULL
);
CREATE INDEX idx_method_name ON log (method_name);