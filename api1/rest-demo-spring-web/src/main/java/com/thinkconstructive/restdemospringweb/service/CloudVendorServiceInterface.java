package com.thinkconstructive.restdemospringweb.service;

import java.util.List;

public interface CloudVendorServiceInterface <T> {
    public String createCloudVendor(T cloudVendor);
    public String updateCloudVendor(T cloudVendor);
    public String deleteCloudVendor(Integer cloudVendorId);
    public T getCloudVendor(Integer cloudVendorId);
    public List<T> getAllCloudVendors();
    public void deleteAll();
}
