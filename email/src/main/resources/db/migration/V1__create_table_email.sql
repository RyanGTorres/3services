CREATE TABLE tb_email (
    email_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL,
    email_from VARCHAR(255) NOT NULL,
    email_to VARCHAR(255) NOT NULL,
    email_subject VARCHAR(255),
    body TEXT,
    email_status VARCHAR(20) DEFAULT 'ENVIADO',
    sended_at TIMESTAMP
);