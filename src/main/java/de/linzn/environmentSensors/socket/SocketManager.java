/*
 * Copyright (C) 2020. Niklas Linz - All Rights Reserved
 * You may use, distribute and modify this code under the
 * terms of the LGPLv3 license, which unfortunately won't be
 * written for another century.
 *
 * You should have received a copy of the LGPLv3 license with
 * this file. If not, please write to: niklas.linz@enigmar.de
 *
 */

package de.linzn.environmentSensors.socket;

import de.linzn.environmentSensors.EnvironmentSensorsApp;
import de.linzn.environmentSensors.socket.listener.ConnectionListener;
import de.linzn.environmentSensors.socket.listener.DataListener;
import de.linzn.zSocket.components.encryption.CryptContainer;
import de.linzn.zSocket.connections.client.ClientConnection;

public class SocketManager {
    private ClientConnection clientConnection;
    private String host;
    private int port;
    private CryptContainer cryptContainer;

    public SocketManager() {
        this.host = EnvironmentSensorsApp.appConfigurationModule.socketHost;
        this.port = EnvironmentSensorsApp.appConfigurationModule.socketPort;
        this.cryptContainer = new CryptContainer(EnvironmentSensorsApp.appConfigurationModule.cryptAESKey, EnvironmentSensorsApp.appConfigurationModule.vector16B);
        this.clientConnection = new ClientConnection(this.host, this.port, new SocketMask(), this.cryptContainer);
        this.clientConnection.registerEvents(new ConnectionListener());
        this.clientConnection.registerEvents(new DataListener());
    }

    public void startConnection() {
        this.clientConnection.setEnable();
    }

    public void stopConnection() {
        this.clientConnection.setDisable();
    }
}
