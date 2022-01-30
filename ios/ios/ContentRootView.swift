//
//  ContentRootView.swift
//  ios
//
//  Created by Lonely Astronaut on 31.01.22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct ContentRootView: View {
    private let authComponent: AuthComponent
    
    @State private var username = ""
    @State private var password = ""
    
    @ObservedObject
    private var observedState: ObservableValue<AuthState>
    
    init(_ authComponent: AuthComponent) {
        self.authComponent = authComponent
        observedState = ObservableValue(authComponent.state)
    }
    
    var body: some View {
        VStack {
            TextField("Login", text: $username)
            TextField("Password", text: $password)
            Text("\(observedState.value)")
            Button("Login?", action: {
                authComponent.login(username: username, password: password)
            })
        }
    }
}
