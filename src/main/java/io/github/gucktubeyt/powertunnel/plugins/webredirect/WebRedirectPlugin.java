package io.github.gucktubeyt.powertunnel.plugins.webredirect;

import io.github.krlvm.powertunnel.sdk.configuration.Configuration;
import io.github.krlvm.powertunnel.sdk.plugin.PowerTunnelPlugin;
import io.github.krlvm.powertunnel.sdk.proxy.ProxyServer;
import io.github.krlvm.powertunnel.sdk.utiities.TextReader;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

public class WebRedirectPlugin extends PowerTunnelPlugin {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebRedirectPlugin.class);

    public void onProxyInitialization(@NotNull ProxyServer proxy) {
        if (!proxy.areHostnamesAvailable()) {
            LOGGER.warn("Hosts plugin is not available when VPN-level hostname resolving is enabled");
            return;
        }

        final Configuration config = readConfiguration();

        final String webTarget = config.get("URLTarget", "www.growtopia1.com");
        final String[] redirectAddr = config.get("redirectAddress", "127.0.0.1:8080").split(":", 2);

        registerProxyListener(new DNSListener(webTarget, redirectAddr[0], Integer.valueOf(redirectAddr[1])), -10);
    }
}
