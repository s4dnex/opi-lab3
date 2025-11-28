-- Use only one time for "initialization" of cluster

CREATE INDEX CONCURRENTLY IF NOT EXISTS points_request_time_desc_idx
    ON points (request_time DESC);

ALTER TABLE points
    CLUSTER ON points_request_time_desc_idx;

CLUSTER points USING points_request_time_desc_idx;