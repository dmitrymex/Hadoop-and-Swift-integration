/*
 * Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *  
 *       http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.apache.hadoop.fs.swift.http;

import org.apache.hadoop.classification.InterfaceAudience;
import org.apache.hadoop.classification.InterfaceStability;
import org.apache.http.HttpHeaders;

/**
 * Constants used in the Swift REST protocol,
 * and in the properties used to configure the {@link SwiftRestClient}.
 */
@InterfaceAudience.Public
@InterfaceStability.Evolving
public class SwiftProtocolConstants {
  public static final String HEADER_AUTH_KEY = "X-Auth-Token";
  public static final int SWIFT_HTTP_PORT = 8080;
  public static final int SWIFT_HTTPS_PORT = 443;
  public static final String HEADER_RANGE = HttpHeaders.RANGE;
  public static final String HEADER_DESTINATION = HttpHeaders.DESTINATION;
  public static final String SWIFT_RANGE_HEADER_FORMAT_PATTERN = "bytes=%d-%d";
  public static final String SERVICE_CATALOG_SWIFT = "swift";
  public static final String SERVICE_CATALOG_CLOUD_FILES = "cloudFiles";
  public static final String SERVICE_CATALOG_OBJECT_STORE = "object-store";
  public static final String SWIFT_OBJECT_AUTH_ENDPOINT =
    "/object_endpoint/AUTH_";
  public static final String X_OBJECT_MANIFEST = "X-Object-Manifest";
  public static final String X_CONTAINER_OBJECT_COUNT =
    "X-Container-Object-Count";
  public static final String X_CONTAINER_BYTES_USED = "X-Container-Bytes-Used";

  /**
   * Header to set when requesting the latest version of a file
   */
  public static final String X_NEWEST = "X-Newest";

  /**
   * Prefix for all instance-specific values in the configuration: {@value}
   */
  public static final String SWIFT_SERVICE_PREFIX = "fs.swift.service.";

  public static final String DOT_SERVICE = ".service";
  public static final String DOT_AUTH_URL = ".auth.url";
  public static final String DOT_TENANT = ".tenant";
  public static final String DOT_USERNAME = ".username";
  public static final String DOT_PASSWORD = ".password";
  public static final String DOT_HTTP_PORT = ".http.port";
  public static final String DOT_HTTPS_PORT = ".https.port";
  public static final String DOT_REGION = ".region";

  public static final String SWIFT_SERVICE_PROPERTY = "swift" + DOT_SERVICE;
  public static final String SWIFT_AUTH_PROPERTY = "swift" + DOT_AUTH_URL;
  public static final String SWIFT_TENANT_PROPERTY = "swift" + DOT_TENANT;
  public static final String SWIFT_USERNAME_PROPERTY = "swift" + DOT_USERNAME;
  public static final String SWIFT_PASSWORD_PROPERTY = "swift" + DOT_PASSWORD;
  public static final String SWIFT_HTTP_PORT_PROPERTY = "swift" + DOT_HTTP_PORT;
  public static final String SWIFT_HTTPS_PORT_PROPERTY = "swift"
                                                         + DOT_HTTPS_PORT;
  public static final String SWIFT_REGION_PROPERTY = "swift" + DOT_REGION;

}