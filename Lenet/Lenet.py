# python imports
import os
import torch
import torch.nn as nn
from tqdm import tqdm
import torchvision.transforms as transforms

import numpy as np 

class LeNet(nn.Module):
    def __init__(self, input_shape=(32, 32), num_classes=100):
        super(LeNet, self).__init__()
        # Define layers
        self.conv1 = nn.Conv2d(in_channels=3, out_channels=6, kernel_size=5, stride=1)
        self.maxPool1 = nn.MaxPool2d(kernel_size=2, stride=2)
        self.relu1 = nn.ReLU()

        self.conv2 = nn.Conv2d(in_channels=6, out_channels=16, kernel_size=5, stride=1)
        self.maxPool2 = nn.MaxPool2d(kernel_size=2, stride=2)
        self.relu2 = nn.ReLU()

        self.flatten = nn.Flatten()

        self.fc1 = nn.Linear(16*5*5, 256)
        self.fc1relu = nn.ReLU()

        self.fc2 = nn.Linear(256, 128)
        self.fc2relu = nn.ReLU()

        self.fc3 = nn.Linear(128, num_classes)
        self.fc3relu = nn.ReLU()

    def forward(self, x):
        shape_dict = {}
        out = self.conv1(x)
        out = self.relu1(out)
        out = self.maxPool1(out)
        shape_dict[1] = list(out.size())

        out = self.conv2(out)
        out = self.relu2(out)
        out = self.maxPool2(out)
        shape_dict[2] = list(out.size())

        out = self.flatten(out)
        shape_dict[3] = list(out.size())

        out = self.fc1(out)
        out = self.fc1relu(out)
        shape_dict[4] = list(out.size())

        out = self.fc2(out)
        out = self.fc2relu(out)
        shape_dict[5] = list(out.size())

        out = self.fc3(out)
        out = self.fc3relu(out)
        shape_dict[6] = list(out.size())

        return out, shape_dict

def count_model_params():
    '''
    Return the number of trainable parameters of LeNet.
    '''
    model = LeNet()
    model_params = sum(p.numel() for p in model.parameters() if p.requires_grad)
    return model_params / 1e6  # Convert to millions

def train_model(model, train_loader, optimizer, criterion, epoch):
    """
    Train the model.
    """
    model.train()
    train_loss = 0.0
    for input, target in tqdm(train_loader, total=len(train_loader)):
        optimizer.zero_grad()
        output, _ = model(input)
        loss = criterion(output, target)
        loss.backward()
        optimizer.step()
        train_loss += loss.item()

    train_loss /= len(train_loader)
    print('[Training set] Epoch: {:d}, Average loss: {:.4f}'.format(epoch+1, train_loss))
    return train_loss

def test_model(model, test_loader, epoch):
    """
    Test the model.
    """
    model.eval()
    correct = 0
    with torch.no_grad():
        for input, target in test_loader:
            output, _ = model(input)
            pred = output.max(1, keepdim=True)[1]
            correct += pred.eq(target.view_as(pred)).sum().item()

    test_acc = correct / len(test_loader.dataset)
    print('[Test set] Epoch: {:d}, Accuracy: {:.2f}%\n'.format(epoch+1, 100. * test_acc))
    return test_acc
