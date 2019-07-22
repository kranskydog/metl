/**
 * Licensed to JumpMind Inc under one or more contributor
 * license agreements.  See the NOTICE file distributed
 * with this work for additional information regarding
 * copyright ownership.  JumpMind Inc licenses this file
 * to you under the GNU General Public License, version 3.0 (GPLv3)
 * (the "License"); you may not use this file except in compliance
 * with the License.
 *
 * You should have received a copy of the GNU General Public License,
 * version 3.0 (GPLv3) along with this library; if not, see
 * <http://www.gnu.org/licenses/>.
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.jumpmind.metl.core.runtime.component;

import static org.apache.commons.lang.StringUtils.isNotBlank;
import static org.jumpmind.metl.core.runtime.FlowConstants.REQUEST_VALUE_PARAMETER;

import org.jumpmind.metl.core.runtime.Message;
import org.jumpmind.metl.core.runtime.flow.ISendMessageCallback;

public class HttpRequest extends AbstractHttpRequestResponse implements IHasSecurity {

    public static final String PATH = "path";

    public static final String HTTP_METHOD = "http.method";

    public static final String SECURITY_SCHEME = "security.scheme";

    public static final String SECURE_USERNAME = "secure.username";

    public static final String SECURE_PASSWORD = "secure.password";

    public HttpRequest() {
    }

    @Override
    public SecurityScheme getSecurityType() {
        return SecurityScheme.valueOf(properties.get(SECURITY_SCHEME, SecurityScheme.NONE.name()));
    }

    @Override
    public String getPassword() {
        return properties.get(SECURE_PASSWORD);
    }

    @Override
    public String getUsername() {
        return properties.get(SECURE_USERNAME);
    }

    @Override
    public void handle(Message inputMessage, ISendMessageCallback callback,
            boolean unitOfWorkBoundaryReached) {
        String requestPayload = getComponentContext().getFlowParameters()
                .get(REQUEST_VALUE_PARAMETER);
        if (isNotBlank(requestPayload)) {
            callback.sendTextMessage(inputMessage.getHeader(), requestPayload);
        }
    }

    @Override
    public boolean supportsStartupMessages() {
        return true;
    }

}
