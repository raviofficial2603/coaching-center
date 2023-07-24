package com.awitez.batch.service;

import java.util.List;

import com.awitez.batch.entity.Batch;
import com.awitez.batch.payload.BatchResponse;

public interface BatchService {

	public Batch createBatch(Batch batch);
	public List<BatchResponse> getAllBatchs();
	public Batch getBatch(long id);
	public Batch updateBatch(Batch batch, long id);
	public void deleteBatch(long id);
	public boolean isPresent(long id);
	public BatchResponse getBatchDetails(long id);
}
