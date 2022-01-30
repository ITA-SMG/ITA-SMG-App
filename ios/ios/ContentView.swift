import SwiftUI
import shared

let di = DIRoot()

struct ContentView: View {
    @State
    private var componentHolder = ComponentHolder<AuthComponent>(factory: di.getAuthComponentFactory())
    
    
    var body: some View {
        ContentRootView(componentHolder.component)
            .onAppear { LifecycleRegistryExtKt.resume(self.componentHolder.lifecycle) }
            .onDisappear { LifecycleRegistryExtKt.stop(self.componentHolder.lifecycle) }
    }
}
