//
//  ObservableValue.swift
//  ios
//
//  Created by Lonely Astronaut on 31.01.22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

public class ObservableValue<T : AnyObject> : ObservableObject {
    private let observableValue: Value<T>
    
    @Published
    var value: T
    
    private var observer: ((T) -> Void)?
    
    init(_ value: Value<T>) {
        self.observableValue = value
        self.value = observableValue.value
        
        self.observer = { value in
            self.value = value
        }
        observableValue.subscribe(observer: observer!)
    }
    
    deinit {
        self.observableValue.unsubscribe(observer: self.observer!)
    }
}
